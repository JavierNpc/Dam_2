import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-selector-imegenes',
  imports: [CommonModule],
  templateUrl: './selector-imegenes.component.html',
  styleUrl: './selector-imegenes.component.css'
})
export class SelectorImegenesComponent {
  imagenes = [
    { id: 'espadachin1', src: 'imagenes/razaElfo.png' },
    { id: 'espadachin2', src: 'imagenes/razaElfo.png' },
    { id: 'espadachin3', src: 'imagenes/razaElfo.png' },
    { id: 'espadachin4', src: 'imagenes/razaElfo.png' }
  ];

  imagenSeleccionada: string = ''; // Almacena el ID de la imagen seleccionada

  seleccionarImagen(id: string) {
    this.imagenSeleccionada = id; // Guarda la imagen seleccionada
    console.log('Imagen seleccionada:', id); // Puedes enviar este string donde lo necesites
  }
}
