import { Component, inject, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Personaje } from '../Personaje';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-personaje-estadisticas',
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './personaje-estadisticas.component.html',
  styleUrl: './personaje-estadisticas.component.css'
})
export class PersonajeEstadisticasComponent implements OnInit {

  formEstadisticas! : FormGroup

  constructor(private dataService:DataService){}

  ngOnInit() {
    this.dataService.fomrEstadisticas$.subscribe( formE =>{
      this.formEstadisticas = formE
    })
  }

  submitApplication() {
    this.dataService.actualizarFormEstadisticas(this.formEstadisticas)
    console.log("Hey funciono", this.formEstadisticas.value);
    this.dataService.ActualizarMensajePersonaje('habilidades')
  }

}
