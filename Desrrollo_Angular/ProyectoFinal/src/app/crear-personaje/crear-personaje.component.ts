import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { NavbarPersonajesComponent } from "../navbar-personajes/navbar-personajes.component";
import { CommonModule, NgSwitch } from '@angular/common';
import { PersonajeRazaComponent } from "../personaje-raza/personaje-raza.component";
import { PersonajeEstadisticasComponent } from "../personaje-estadisticas/personaje-estadisticas.component";
import { PersonajeHabilidadesComponent } from "../personaje-habilidades/personaje-habilidades.component";
import { PersonajeNombreComponent } from "../personaje-nombre/personaje-nombre.component";
import { DataService } from '../data.service';
import { ResumenCreacionPersonajeComponent } from "../resumen-creacion-personaje/resumen-creacion-personaje.component";
@Component({
  selector: 'app-crear-personaje',
  imports: [NavbarComponent, NavbarPersonajesComponent, NgSwitch, CommonModule, PersonajeRazaComponent, PersonajeEstadisticasComponent, PersonajeHabilidadesComponent, PersonajeNombreComponent, ResumenCreacionPersonajeComponent],
  templateUrl: './crear-personaje.component.html',
  styleUrl: './crear-personaje.component.css'
})
export class CrearPersonajeComponent implements OnInit {
 

  conditionExpression: any;
  terminado: any;

  constructor(private dataService:DataService){}

  ngOnInit() {
    this.dataService.terminado$.subscribe(valor =>{
      this.terminado = valor
      console.log('Terminado : ' +this.terminado )
    })
 
    this.dataService.mensajeCrearPersonaje$.subscribe(valor => {
      this.conditionExpression = valor
    })
  }

  

  

  
  


 

}
