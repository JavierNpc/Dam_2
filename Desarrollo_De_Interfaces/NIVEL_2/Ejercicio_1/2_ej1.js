function mod_parrafos(){
  const callection = document.querySelectorAll("p")
  const name = document.getElementById("caja").value

   for (let index = 0; index < callection.length; index++) {
    callection[index].innerHTML = name
    
   }
   
   
   

}
   