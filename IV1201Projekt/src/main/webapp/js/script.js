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



