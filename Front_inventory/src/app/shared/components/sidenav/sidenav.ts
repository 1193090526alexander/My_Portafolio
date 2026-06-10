import { Component, OnInit, inject, signal } from '@angular/core';
import { MaterialModule } from '../../material/material';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-sidenav',
  standalone: true, // Asegúrate de tenerlo si es un componente standalone
  imports: [
    MaterialModule,
    RouterOutlet,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './sidenav.html',
  styleUrl: './sidenav.css'
})
export class Sidenav implements OnInit {
  // 1. Inicializamos como un Signal vacía
  username = signal<string>(''); 
  
  private keycloak = inject(KeycloakService);

  async ngOnInit(): Promise<void> {
    try {
      const isLogged = await this.keycloak.isLoggedIn();

      if (isLogged) {
        const profile = await this.keycloak.loadUserProfile();
        
        // 2. Asignamos el valor usando .set()
        const name = profile.firstName || profile.username || 'Usuario';
        this.username.set(name);
      } else {
        this.username.set('Invitado');
      }
    } catch (error) {
      console.error('Error cargando usuario', error);
      this.username.set('Error al cargar');
    }
  }
}