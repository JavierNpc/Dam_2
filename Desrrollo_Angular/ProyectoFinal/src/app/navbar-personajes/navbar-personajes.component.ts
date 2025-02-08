import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-navbar-personajes',
  imports: [CommonModule],
  templateUrl: './navbar-personajes.component.html',
  styleUrl: './navbar-personajes.component.css'
})
export class NavbarPersonajesComponent implements OnInit {

  botonSeleccionado:any
  //  Constructor
  constructor(private dataService:DataService){ }


  botones: string[] = ['raza', 'estadisticas', 'habilidades', 'nombre'];
 

  guardarNombre(nombre: string) {
    this.botonSeleccionado = nombre;
    this.dataService.ActualizarMensajePersonaje(nombre)
    console.log('Botón clicado:', nombre);
  }

  ActualizarNombre(nombre:string) {
    this.botonSeleccionado = nombre
  }

  ngOnInit() {
    this.dataService.botonSeleccionado$.subscribe(valor => {
      this.botonSeleccionado = valor
    })
    console.log(this.botonSeleccionado)
  }



  
  
}
