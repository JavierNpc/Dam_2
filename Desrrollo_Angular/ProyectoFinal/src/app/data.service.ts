import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Personaje } from './Personaje';
import { FormControl, FormGroup } from '@angular/forms';
import { fail } from 'assert';

@Injectable({
  providedIn: 'root'
})

export class DataService implements OnInit {

  // Datos de los personajes
  //--------------------------------------------------------------------------

  private raza = new BehaviorSubject<string>('No raza')
  raza$ = this.raza.asObservable()
  actualizarRaza(newRaza :string){
    this.raza.next(newRaza)
  }
  obtenerRaza(): string{
    return this.raza.value
  }

  //--------------------------------------------------------------------------

  private fomrEstadisticas = new BehaviorSubject<FormGroup>(new FormGroup({
    vit: new FormControl(10),
    str: new FormControl(10),
    des: new FormControl(10),
    res: new FormControl(10),
    arc: new FormControl(10)
  }));
  fomrEstadisticas$ = this.fomrEstadisticas.asObservable()
  actualizarFormEstadisticas(formNew: FormGroup){
    this.fomrEstadisticas.next(formNew)
  }
  obtenerFormularioEstadisticas(): FormGroup {
    return this.fomrEstadisticas.value
  }

  //--------------------------------------------------------------------------

  private habilidad = new BehaviorSubject<string>('No hab')
  habilidad$ = this.habilidad.asObservable()
  actualizarHabilidad(newHab :string){
    this.habilidad.next(newHab)
  }
  obtenerHabilidad(){
    console.log(this.habilidad)
    return this.habilidad.value
  }

  //--------------------------------------------------------------------------

  private fomrNombre = new BehaviorSubject<FormGroup>(new FormGroup({
    nombre: new FormControl('Sin Nombre'),
    edad: new FormControl(0)
  }));
  fomrNombre$ = this.fomrNombre.asObservable()
  actualizarFormNombre(newFormN: FormGroup){
    this.fomrNombre.next(newFormN)
  } 
  obtenerFormularioNombre(): FormGroup {
    return this.fomrNombre.value
    
  }

  //--------------------------------------------------------------------------


  //Creacion e inserccion de nuevos personajes

  protected id: number = 0

  ngOnInit() {
    this.id = this.getAllPersonajes().length
    console.log("numero de personajes = " + this.id)
  }

  protected personajeList: Personaje[] = [
    {
      id: 0,
      nombre: 'Ajaks',
      edad : 1000,
      raza: 'Elfo',
      estadisticas: [
        10,
        10,
        10,
        10,
        10
      ],
      habilidad: 'Sigilo'
    },
    {
      id: 1,
      nombre: 'Ajaks',
      edad : 1000,
      raza: 'Elfo',
      estadisticas: [
        10,
        10,
        10,
        10,
        10
      ],
      habilidad: 'Sigilo'
    },

  ]

  getAllPersonajes():Personaje[] {
    return this.personajeList
  }

  getPersonajeById(id: number): Personaje | undefined {
    return this.personajeList.find((personaje) => personaje.id === id);
  }

  AgregarPersonaje( 
    nombre: string,
    edad : number,
    raza: string,
    estadisticas:[
      vit: number,
      str: number,
      des: number,
      res: number,
      arc: number
    ],
    habilidad: string
  ){
   
   this.personajeList.push(
    {
      id: this.id + 1,
      edad : edad,
      nombre: nombre,
      raza: raza,
      estadisticas: estadisticas,
      habilidad : habilidad
    },
   )
   console.log(estadisticas)
  }

  //--------------------------------------------------------------------------

  // Seleccion navbarPersonaje

  private mensajeCrearPersonaje = new BehaviorSubject<string>('Valor Inicial')
  private botonSeleccionado = new BehaviorSubject<string>("raza")
  private terminado = new BehaviorSubject<boolean>(false)
 
  botonSeleccionado$ = this.botonSeleccionado.asObservable()
  mensajeCrearPersonaje$ = this.mensajeCrearPersonaje.asObservable()
  terminado$ = this.terminado.asObservable()

  constructor() { }

  ActualizarMensajePersonaje(newMensaje: string){
    this.mensajeCrearPersonaje.next(newMensaje)
    this.botonSeleccionado.next(newMensaje)
  }

  PersonajeTerminado(cond:boolean){
    this.terminado.next(cond)
  }



 
 
}
