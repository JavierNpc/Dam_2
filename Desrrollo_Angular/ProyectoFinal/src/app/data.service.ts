import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Personaje } from './Personaje';
import { FormControl, FormGroup } from '@angular/forms';
import { fail } from 'assert';


@Injectable({
  providedIn: 'root'
})

export class DataService {

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
    vit: new FormControl(''),
    str: new FormControl(''),
    des: new FormControl(''),
    res: new FormControl(''),
    arc: new FormControl('')
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
    return this.habilidad
  }

  //--------------------------------------------------------------------------

  private fomrNombre = new BehaviorSubject<FormGroup>(new FormGroup({
    nombre: new FormControl(),
    edad: new FormControl()
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

  protected personajeList: Personaje[] = [
    {
      id: 1,
      nombre: 'Ajaks',
      raza: 'Elfo',
      estadisticas: [
        10,
        10,
        10,
        10,
        10
      ],
      habilidades: [
        'Sigilo',
        'Robo'
      ]
    },

  ]

  getAllPersonajes():Personaje[] {
    return this.personajeList
  }

  getHousingLocationById(id: number): Personaje | undefined {
    return this.personajeList.find((personaje) => personaje.id === id);
  }

  addHouse( 
    id: number,
    nombre: string,
    raza: string,
    estadisticas:[
      vit: number,
      str: number,
      des: number,
      res: number,
      arc: number
    ],
    habilidades: [
      hab1:string,
      hab2:string
    ]
  ){
   this.personajeList.push(
    {
      id: id,
      nombre: nombre,
      raza: raza,
      estadisticas: estadisticas,
      habilidades : habilidades
    },
   )
  }


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
