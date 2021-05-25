document.getElementById("achats_radio").addEventListener("change", modifyClassAchats);
document.getElementById("ventes_radio").addEventListener("change", modifyClassVentes);

function modifyClassAchats(){
    console.log("test1")
    document.getElementById("bid").classList.remove("disabledButton")
    document.getElementById("sell").classList.add("disabledButton")
}

function modifyClassVentes(){
    console.log("test2")
    document.getElementById("bid").classList.add("disabledButton")
    document.getElementById("sell").classList.remove("disabledButton")
}