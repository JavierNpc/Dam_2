import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar-personajes',
  imports: [CommonModule],
  templateUrl: './navbar-personajes.component.html',
  styleUrl: './navbar-personajes.component.css'
})
export class NavbarPersonajesComponent {
  botones: string[] = ['raza', 'estadisticas', 'habilidades', 'nombre'];
  botonSeleccionado: string = '';

  guardarNombre(nombre: string) {
    this.botonSeleccionado = nombre;
    console.log('Botón clicado:', nombre);
  }
  
}
