import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { DataService } from '../data.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-personaje-nombre',
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './personaje-nombre.component.html',
  styleUrl: './personaje-nombre.component.css'
})
export class PersonajeNombreComponent {


  formNombre! : FormGroup

  constructor(private dataService:DataService){}

  ngOnInit() {
    this.dataService.fomrNombre$.subscribe( formE =>{
      this.formNombre = formE
    })
  }

  submitApplication() {
    this.dataService.actualizarFormNombre(this.formNombre)
    console.log("Hey funciono", this.formNombre.value);
    this.dataService.PersonajeTerminado(true)
  }

}
