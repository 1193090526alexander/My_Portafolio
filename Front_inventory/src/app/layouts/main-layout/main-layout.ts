import { Component, inject, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { RouterOutlet } from '@angular/router';
import { MaterialModule } from '../../shared/material/material';
import { Sidenav } from '../../shared/components/sidenav/sidenav';
// 1. Importamos el servicio
import { KeycloakService } from 'keycloak-angular'; 

@Component({
  selector: 'app-main-layout',
  imports: [
    RouterOutlet,
    MaterialModule,
    Sidenav
  ],
  templateUrl: './main-layout.html',
  styleUrl: './main-layout.css'
})
export class MainLayout implements OnInit {

  isMobile = false;
  sidenavMode: 'over' | 'side' = 'side';
  sidenavOpened = true;

  private breakpointObserver = inject(BreakpointObserver);
  // 2. Inyectamos el servicio global de Keycloak
  private keycloak = inject(KeycloakService); 

  ngOnInit(): void {
    this.breakpointObserver
      .observe([Breakpoints.Handset])
      .subscribe(result => {
        this.isMobile = result.matches;
        this.sidenavMode = this.isMobile ? 'over' : 'side';
        this.sidenavOpened = !this.isMobile;
      });
  }

  // 3. Modificamos el método para usar la librería de forma dinámica
  logout(): void {
    // window.location.origin le dice a Keycloak que regrese de forma automática 
    // a la raíz de tu app (ej: http://localhost:4200) tras cerrar sesión.
    this.keycloak.logout(window.location.origin);
  }
}