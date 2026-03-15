const info_pilote_openf1 = "https://api.openf1.org/v1/drivers?session_key=latest";
const classement_pilote = "https://api.jolpi.ca/ergast/f1/2026/driverstandings/";




async function fetchClassement() {
    try {
        const res= await fetch(classement_pilote);
        if(!res.ok) throw new Error('API error');
        const json = await res.json();
        const classement = json.MRData.StandingsTable.StandingsLists[0].DriverStandings;
       

        const dataTable = document.getElementById('classement');
        let html = ``;
        
        const pilotes = await fetchPilotes();
        
        for(let i=0;i< classement.length;i+=2){
            const gauche = classement[i];
            const droite= classement[i+1];

            //Croisement des données entres les deux apis pour récupérer les photos (deux api car l'api de classement ne contient pas les photos des pilotes, il faut donc aller les chercher dans l'api des pilotes et l'autres n'a pas les données de classement à jours)
            const pilotesOpenF1_gauche = pilotes.find(p => p.last_name.toUpperCase() === gauche.Driver.familyName.toUpperCase()); //pareil qu'un fort each pour trouver le pilote de droite
            const pilotesOpenF1_droite = pilotes.find(p => p.last_name.toUpperCase() === droite.Driver.familyName.toUpperCase()); //pareil qu'un fort each pour trouver le pilote de droite
            const photo_gauche = pilotesOpenF1_gauche ? pilotesOpenF1_gauche.headshot_url : "";
            const photo_droite = pilotesOpenF1_droite ? pilotesOpenF1_droite.headshot_url : "";
            
            html += `

                <div class="grille-ligne"> 

                    <div class="pilote-position">${gauche.position}</div>
                    
                    <div class="pilote-gauche">
                        
                        <img src="${photo_gauche}" alt="Photo de ${gauche.Driver.givenName} ${gauche.Driver.familyName}" class="pilote-photo"></img>    
                        
                        <div class="pilote-info">
                            <div class="pilote-nom">  ${gauche.Driver.givenName} ${gauche.Driver.familyName} (${gauche.Driver.permanentNumber})</div>
                            <div class="pilote-nationalite"> Nationalité: ${gauche.Driver.nationality}</div>
                            <div class="pilote-ecurie"> Ecurie: ${gauche.Constructors[0].name}</div>
                            <div class="pilote-points"> Points: ${gauche.points}</div>
                            <div class="pilote-victoires"> Victoires: ${gauche.wins}</div>
                        </div>

                    </div>
                    

                    <div id="droite" class="pilote-position">${droite.position}</div>

                    <div  class="pilote-droite">
                        <img src="${photo_droite}" alt="Photo de ${droite.Driver.givenName} ${droite.Driver.familyName}" class="pilote-photo"></img>  

                        <div class="pilote-info">
                            <div class="pilote-nom">  ${droite.Driver.givenName} ${droite.Driver.familyName} (${droite.Driver.permanentNumber})</div>
                            <div class="pilote-nationalite"> Nationalité: ${droite.Driver.nationality}</div>
                            <div class="pilote-ecurie"> Ecurie: ${droite.Constructors[0].name}</div>
                            <div class="pilote-points"> Points: ${droite.points}</div>
                            <div class="pilote-victoires"> Victoires: ${droite.wins}</div>
                        </div>

                    </div>
                    

                </div>
               
            `;
        
            
        };

        dataTable.innerHTML = html;
        //renderClassement(classement);

    } catch (e) {
        console.error(e);    }
}

async function fetchPilotes() {
    const res= await fetch(info_pilote_openf1);
        if(!res.ok) throw new Error('API error');
        const json = await res.json();
        return json;

}

window.addEventListener('DOMContentLoaded', () => {
    fetchClassement() });