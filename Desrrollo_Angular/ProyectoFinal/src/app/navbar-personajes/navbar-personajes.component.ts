import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-navbar-personajes',
  imports: [CommonModule],
  templateUrl: './navbar-personajes.component.html',
  styleUrl: './navbar-personajes.component.css'
})
export class NavbarPersonajesComponent {

  //  Constructor
  constructor(private dataService:DataService){}

  botones: string[] = ['raza', 'estadisticas', 'habilidades', 'nombre'];
  botonSeleccionado: string = 'raza';



  guardarNombre(nombre: string) {
    this.botonSeleccionado = nombre;
    this.dataService.ActualizarMensajePersonaje(nombre)
    console.log('Botón clicado:', nombre);
  }

  
  
}
