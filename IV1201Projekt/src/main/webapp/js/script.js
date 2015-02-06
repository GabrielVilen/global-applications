/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function radio_button_check(id) {
    if (id === 'recruiter') {
        document.getElementById("comp_prof").style.display = 'none';
    } else {
        document.getElementById("comp_prof").style.display = 'block';
    }
}

function addCompetence(){
    var skill = $("#competenceList option:selected").text();
    var numOfYears=$("#competenceYears").val();
    $("#competenceTable tbody").append('<tr><td>'+skill +'</td><td>'+numOfYears+'</td><td><input type="hidden" name="skill[]" value="'+skill+'"/>\n\
        <input type="hidden" name="skill[]" value="'+numOfYears+'"/></tr>');
}


