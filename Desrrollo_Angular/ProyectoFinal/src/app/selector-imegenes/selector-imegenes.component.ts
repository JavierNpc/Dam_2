import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DataService } from '../data.service';
import { NavbarPersonajesComponent } from '../navbar-personajes/navbar-personajes.component';



@Component({
  selector: 'app-selector-imegenes',
  imports: [CommonModule],
  templateUrl: './selector-imegenes.component.html',
  styleUrl: './selector-imegenes.component.css'
})
export class SelectorImegenesComponent implements OnInit {

  constructor(private dataService:DataService){}

  raza!: string  

  imagenes = [
    { id: 'Elfo', src: 'imagenes/razaElfo.png' },
    { id: 'Enano', src: 'imagenes/razaEnano.jpg' },
    { id: 'Orco', src: 'imagenes/razaOrco.png' },
    { id: 'Espiritu', src: 'imagenes/razaEspiritu.png' }
  ];

 

  ngOnInit() {
    this.dataService.raza$.subscribe( raz =>{
      this.raza = raz
      console.log("Vengo del servidor soy : "+raz)
    })
  }

  seleccionarImagen(id: string) {
    this.raza = id;
    this.dataService.actualizarRaza(id) // Guarda la imagen seleccionada
    console.log('Imagen seleccionada:', id); // Puedes enviar este string donde lo necesites
    this.dataService.ActualizarMensajePersonaje('estadisticas')
  }

  

 


}
