export interface Personaje{
    id: number
    nombre: string
    edad: number
    raza: string
    estadisticas:[
        vit: number,
        str: number,
        des: number,
        res: number,
        arc: number
    ]
    habilidad:string
}