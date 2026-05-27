import { Component, OnInit } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { MaterialModule } from '../../shared/material/material';
import { Sidenav } from '../../shared/components/sidenav/sidenav';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';

@Component({
  selector: 'app-main-layout',
  imports: [RouterOutlet, RouterLink, RouterLinkActive, MaterialModule, Sidenav],
  templateUrl: './main-layout.html',
  styleUrl: './main-layout.css',
})
export class MainLayout implements OnInit {

  isMobile = false;
  sidenavMode: 'over' | 'side' = 'side';
  sidenavOpened = true;

  constructor(private breakpointObserver: BreakpointObserver) {}

  ngOnInit(): void {
    setTimeout(() => {
      this.breakpointObserver
        .observe([Breakpoints.Handset])
        .subscribe(result => {
          this.isMobile = result.matches;
          this.sidenavMode = this.isMobile ? 'over' : 'side';
          this.sidenavOpened = !this.isMobile;
        });
    });
  }
}