public class Euler{

    private void first(){
        for(int i = 0; i < planets.length-1; i++) {
            Planet planet = planets[i];
            planet.x += planet.veloX * timestep;
            planet.y += planet.veloY * timestep;
            planet.z += planet.veloZ * timestep;
        }

        if(launch){
            planets[11].x += planets[11].veloX * timestep;
            planets[11].y += planets[11].veloY * timestep;
            planets[11].z += planets[11].veloZ * timestep;
        }
    }

    private void second() {
        double G = 6.67e-11;

        double dx;
        double dy;
        double dz;
        double dDist;
        double constant;

        for(int i = 0; i < planets.length-1; i ++){
            planets[i].accX = 0;
            planets[i].accY = 0;
            planets[i].accZ = 0;

            for (int j = 0; j < planets.length-1; j++){
                if(planets[j] != planets[j+1]){
                    dx = planets[j+1].x - planets[j].x;
                    dy = planets[j+1].y - planets[j].y;
                    dz = planets[j+1].z - planets[j].z;

                    dDist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz ,2));
                    constant = G * planets[j+1].mass / Math.pow(dDist, 2);

                    planets[j].accX += dx / dDist * constant;
                    planets[j].accY += dy / dDist * constant;
                    planets[j].accZ += dz / dDist * constant;
                }
            }
        }

        if(launch) {
            planets[11].accX = 0;
            planets[11].accY = 0;
            planets[11].accZ = 0;

            dx = planets[11].x / timestep;
            dy = planets[11].y / timestep;
            dz = planets[11].z / timestep;

            dDist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz ,2));
            constant = G * planets[11].mass / Math.pow(dDist, 2);

            planets[11].accX += dx / dDist * constant;
            planets[11].accY += dy / dDist * constant;
            planets[11].accZ += dz / dDist * constant;
        }
    }

    public void third(){
        for(int i = 0; i < planets.length - 1; i++) {
            Planet planet = planets[i];
            planet.veloX += planet.accelX * timestep;
            planet.veloY += planet.accelY * timestep;
            planet.veloZ += planet.accelZ * timestep;
        }

        if(launch){
            planets[11].veloX += planets[11].accelX * timestep;
            planets[11].veloY += planets[11].accelY * timestep;
        }
    }
}
