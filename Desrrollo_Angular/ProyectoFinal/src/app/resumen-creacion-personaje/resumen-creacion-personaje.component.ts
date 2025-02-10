import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { CrearPersonajeComponent } from '../crear-personaje/crear-personaje.component';
import { CommonModule } from '@angular/common';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-resumen-creacion-personaje',
  imports: [],
  templateUrl: './resumen-creacion-personaje.component.html',
  styleUrl: './resumen-creacion-personaje.component.css'
})
export class ResumenCreacionPersonajeComponent implements OnInit {

  raza! : string
  habilidad!: string
  formEstadisticas! : FormGroup
  formNombre! : FormGroup

  constructor(private dataService:DataService){}

  //Recoger paramentros

  ngOnInit() {
   this.raza = this.dataService.obtenerRaza()
   this.habilidad = this.dataService.obtenerHabilidad()
   this.formEstadisticas = this.dataService.obtenerFormularioEstadisticas()
   this.formNombre = this.dataService.obtenerFormularioNombre()
  }

 


  //  Funcionalidades
  volver() {
   this.dataService.PersonajeTerminado(false)
  }


  CrearPersonaje() {
    this.dataService.AgregarPersonaje(
      this.formNombre.get('nombre')?.value,
      this.formNombre.get('edad')?.value,
      this.raza,
      this.formEstadisticas.value,
      this.habilidad
    )
  }

}
