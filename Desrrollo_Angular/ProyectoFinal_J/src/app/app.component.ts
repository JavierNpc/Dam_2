import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { PrincipalComponent } from './principal/principal.component';

@Component({
  selector: 'app-root',
  imports: [RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ProyectoFinal_J';
}
