import java.util.Random;

public class Main {

    public static int bossHealth = 900;
    public static int bossDamage = 60;
    public static String bossDefence;
    public static int[] heroesHealth = {280, 270, 250,350,400,200,250,300};
    public static int[] heroesDamage = {10, 15, 20, 0,5,20,0,30};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medic","Golem","Lucky","Witcher","Thor"};
    public static int roundNumber;

    public static void main(String[] args) {
        showStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        Witcher();
        chooseBossDefence();
        medicHelp();
        bossHits();
        golem();
        Lucky();
        heroesHit();
        Thor();
        showStatistics();

    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
    }


    public static void golem(){
        int golemlite = 0;
        boolean getOneShot = false;

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] < 0){
                heroesHealth[4] = 0;
                if (heroesHealth[4] > 0 ){
                    if(heroesAttackType[i] != "Golem"){
                        if (!getOneShot) {
                            golemlite = bossDamage %5;
                            heroesHealth[4] -= golemlite;
                            getOneShot = true;
                            System.out.println("Golem use his power");
                        }
                    }
                }

            }
        }

    }
    public static void Lucky(){
        Random random = new Random();
        boolean luckyRandom = random.nextBoolean();
        if (luckyRandom){
            if (heroesHealth[5] > 0){
                heroesHealth[5] += bossDamage;
                System.out.println("Lucky used his power");
            }
        }
    }
    public static void Witcher(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] == 0){
                if (heroesHealth[7] > 0){
                    heroesHealth[i] += heroesHealth[7];
                    System.out.println("witcher use his power");

                }
            }



        }

    }
    public static void Thor(){
        Random random= new Random();
        boolean thorRandom = random.nextBoolean();

        if (thorRandom){
            for (int i = 0; i <heroesDamage[i] ; i++) {
                heroesHealth[i] += bossDamage;


            }
            System.out.println("thor use his power");

        }
    }
    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void medicHelp() {
        int helping = 50;
        boolean hitOneRound = false;

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[3] > 0){
                if ( heroesHealth[i]< 100 && heroesHealth[i]>0){
                    if(!hitOneRound){
                        if (heroesAttackType[i] != "Medic") {
                            heroesHealth[i] += helping;
                            hitOneRound = true;
                            System.out.println(" Medic help to someone");
                        }
                    }
                }

            }

        }

    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void showStatistics() {
        System.out.println("ROUND " + roundNumber + " -------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " +
                (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }
}
