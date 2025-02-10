import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { CrearPersonajeComponent } from '../crear-personaje/crear-personaje.component';
import { CommonModule } from '@angular/common';
import { FormGroup, FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Personaje } from '../Personaje';
@Component({
  selector: 'app-resumen-creacion-personaje',
  imports: [RouterModule,FormsModule],
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
   this.formNombre = this.dataService.obtenerFormularioNombre()
   this.formEstadisticas = this.dataService.obtenerFormularioEstadisticas()
  }

 


  //  Funcionalidades
  volver() {
   this.dataService.PersonajeTerminado(false)
  }


  CrearPersonaje() {
    this.dataService.ActualizarMensajePersonaje('raza')
    this.dataService.PersonajeTerminado(false)
    const nuevoPersonaje : Personaje = {
      id: this.dataService.getAllPersonajes().length ,
      nombre : this.formNombre.get('nombre')?.value,
      edad : this.formNombre.get('edad')?.value,
      raza : this.raza,
      estadisticas: [
        this.formEstadisticas.get('vit')?.value,
        this.formEstadisticas.get('str')?.value,
        this.formEstadisticas.get('des')?.value,
        this.formEstadisticas.get('res')?.value,
        this.formEstadisticas.get('arc')?.value

      ],
      habilidad :this.habilidad
    }

    this.dataService.AgregarPersonaje(nuevoPersonaje)

    
    
  }

}
