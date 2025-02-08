import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { CrearPersonajeComponent } from '../crear-personaje/crear-personaje.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-resumen-creacion-personaje',
  imports: [],
  templateUrl: './resumen-creacion-personaje.component.html',
  styleUrl: './resumen-creacion-personaje.component.css'
})
export class ResumenCreacionPersonajeComponent implements OnInit {

  raza! : String
  constructor(private dataService:DataService){}


  ngOnInit() {
   this.raza = this.dataService.obtenerRaza()
  }

  CrearPersonaje() {
    throw new Error('Method not implemented.');
  }

  volver() {
   this.dataService.PersonajeTerminado(false)
  }


}
