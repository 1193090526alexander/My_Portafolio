import { Component } from '@angular/core';
import { MaterialModule } from '../../../../shared/material/material';

@Component({
  selector: 'app-dashboard',
  imports: [MaterialModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {}
