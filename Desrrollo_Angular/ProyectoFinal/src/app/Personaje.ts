export interface Personaje{
    id: number
    nombre: string
    raza: string
    estadisticas:[
        vit: number,
        str: number,
        des: number,
        res: number,
        arc: number
    ]
    habilidades: [
        hab1: string,
        hab2: string
    ]
}