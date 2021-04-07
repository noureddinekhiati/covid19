const map_token = "pk.eyJ1IjoiYmFsYWxhMTU2NCIsImEiOiJja242azlzd3UwNnNsMnBwZTl5dDRmNG55In0.LjJmDxTcNyWrMyvlQvCK4g"
mapboxgl.accessToken = map_token;

var map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/streets-v11',
    zoom: 4,
    center: [5, 45]
});

function get_cases_per_country(json_data, attr) {
    json_data.forEach(country => {

        let lat = country['coordinates']['latitude'];
        let long = country['coordinates']['longitude'];
        let cases = country['today'][attr];


        if (lat != 0 && long != 0)
            if (cases == 0) {
                let el = document.createElement('div');
                el.className = 'no_cluster';
                el.setAttribute('data-value', cases);
                el.style.width = "20px"; 
                el.style.height = "20px"; 
                
                new mapboxgl.Marker({
                    element: el,
                    scale: 1
                }).setLngLat([long, lat]).addTo(map);
            }
            else {
                if ( (attr == "confirmed" && cases >= 5000 ) ||(attr == "deaths" && cases >= 100) ) {
                    let el = document.createElement('div');
                    el.className = 'hot_cluster';
                    el.setAttribute('data-value', cases);
                    
                    new mapboxgl.Marker({
                        element: el,
                        scale: 1.3
                    }).setLngLat([long, lat]).addTo(map);
                }
                else {
                    let el = document.createElement('div');
                    el.className = 'regular_cluster';

                    if (cases < 10) {
                        el.style.width = "25px"; 
                        el.style.height = "25px"; 
                    }
                    else {
                        el.style.width = "40px"; 
                        el.style.height = "40px"; 
                    }
                    el.setAttribute('data-value', cases);

                    new mapboxgl.Marker({
                        element: el,
                        scale: 1.1
                    }).setLngLat([long, lat]).addTo(map);
                }
            }
    });
}

fetch("https://corona-api.com/countries")
.then(d => d.json())
.then(d => get_cases_per_country(d['data'], "confirmed"));
