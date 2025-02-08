import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-selecccion-habilidades',
  imports: [CommonModule],
  templateUrl: './selecccion-habilidades.component.html',
  styleUrl: './selecccion-habilidades.component.css'
})
export class SelecccionHabilidadesComponent implements OnInit {

  constructor(private dataService:DataService){}

  habilidad! : string 

  imagenes = [
    { id: 'Sigilo', src: 'imagenes/sigilo.png' },
    { id: 'Teleport', src: 'imagenes/teleport.png' },
    { id: 'Robo', src: 'imagenes/robo.png' },
    { id: 'Elocuencia', src: 'imagenes/elocuencia.png' }
  ];

 

  ngOnInit() {
    this.dataService.habilidad$.subscribe( hab =>{
      this.habilidad = hab
      console.log("Vengo del servidor soy : "+hab)
    })
  }

  seleccionarImagen(id: string) {
    this.habilidad = id;
    this.dataService.actualizarHabilidad(id) // Guarda la imagen seleccionada
    console.log('Imagen seleccionada:', id); // Puedes enviar este string donde lo necesites
    this.dataService.ActualizarMensajePersonaje('nombre')
  }


}
