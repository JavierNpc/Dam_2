import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { AppComponent } from "../app.component";
import { RouterOutlet } from '@angular/router';
import { NavbarPersonajesComponent } from "../navbar-personajes/navbar-personajes.component";
import { CommonModule, NgSwitch } from '@angular/common';
import { PersonajeRazaComponent } from "../personaje-raza/personaje-raza.component";
import { PersonajeEstadisticasComponent } from "../personaje-estadisticas/personaje-estadisticas.component";
import { PersonajeHabilidadesComponent } from "../personaje-habilidades/personaje-habilidades.component";
import { PersonajeNombreComponent } from "../personaje-nombre/personaje-nombre.component";

@Component({
  selector: 'app-crear-personaje',
  imports: [NavbarComponent, NavbarPersonajesComponent, NgSwitch, CommonModule, PersonajeRazaComponent, PersonajeEstadisticasComponent, PersonajeHabilidadesComponent, PersonajeNombreComponent],
  templateUrl: './crear-personaje.component.html',
  styleUrl: './crear-personaje.component.css'
})
export class CrearPersonajeComponent {

 /* Te has quedado aqui */
  conditionExpression: any;
  


 

}
