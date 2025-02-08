import { Component, OnInit } from '@angular/core';
import { SelecccionHabilidadesComponent } from "../selecccion-habilidades/selecccion-habilidades.component";
import { FormGroup } from '@angular/forms';
import { DataService } from '../data.service';

@Component({
  selector: 'app-personaje-habilidades',
  imports: [SelecccionHabilidadesComponent],
  templateUrl: './personaje-habilidades.component.html',
  styleUrl: './personaje-habilidades.component.css'
})
export class PersonajeHabilidadesComponent {

}
