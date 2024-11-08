function ampliar(){
   const alturaIn = parseInt(document.getElementById("div1").style.height) +10
   const alturaFin = String(alturaIn +"px")
   document.getElementById("div1").style.height = alturaFin
}

function reducir(){
   const alturaIn = parseInt(document.getElementById("div1").style.height) -10
   const alturaFin = String(alturaIn +"px")
   document.getElementById("div1").style.height = alturaFin
}


