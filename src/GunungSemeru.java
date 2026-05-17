import java.util.Scanner;

public class GunungSemeru {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // MAP GUNUNG SEMERU
        char[][] map = {

                //0    1    2    3    4    5    6    7    8    9   10   11
                {'P','X','X','X','X','X','X','X','X','1','1','1'}, // y = 0

                {'1','X','1','1','1','X','X','3','X','1','X','1'}, // y = 1

                {'1','X','X','X','1','4','1','1','1','1','X','1'}, // y = 2

                {'1','T','1','1','1','X','1','1','X','X','X','1'}, // y = 3

                {'1','1','X','1','1','X','2','X','X','X','X','1'}, // y = 4

                {'5','1','X','R','1','X','1','1','1','1','1','1'}  // y = 5
        };

        // POSISI AWAL (P1)
        int x = 11;
        int y = 5;

        System.out.print("Masukkan tenaga awal : ");
        int tenaga = input.nextInt();

        input.nextLine();

        System.out.print("Masukkan jalur : ");
        String jalur = input.nextLine();

        boolean selesai = false;

        // MEMBACA SETIAP LANGKAH
        for (int i = 0; i < jalur.length(); i++) {

            char langkah = jalur.charAt(i);

            int nx = x;
            int ny = y;

            // KIRI
            if (langkah == 'L') {
                nx--;
                tenaga--;
            }

            // KANAN
            else if (langkah == 'R') {
                nx++;
                tenaga--;
            }

            // ATAS
            else if (langkah == 'U') {
                ny--;
                tenaga--;
            }

            // BAWAH
            else if (langkah == 'D') {
                ny++;
                tenaga--;
            }

            // ISTIRAHAT
            else if (langkah == 'r') {

                // hanya boleh di pos
                if (map[y][x] == '2' ||
                        map[y][x] == '3' ||
                        map[y][x] == '4' ||
                        map[y][x] == '5' ||
                        map[y][x] == 'R') {

                    tenaga += 10;

                    System.out.println("Istirahat berhasil, tenaga bertambah 10");
                }

                else {

                    System.out.println("Mohon maaf, istirahat hanya diperbolehkan di Pos-pos yang tersedia");
                    selesai = true;
                    break;
                }

                continue;
            }

            // INPUT TIDAK VALID
            else {

                System.out.println("Perintah tidak dikenal");
                selesai = true;
                break;
            }

            // CEK KELUAR MAP
            if (nx < 0 || ny < 0 ||
                    ny >= map.length ||
                    nx >= map[0].length) {

                System.out.println("Jalur anda salah, keluar dari map");
                selesai = true;
                break;
            }

            // CEK JURANG
            if (map[ny][nx] == 'X') {

                System.out.println("Jalur anda salah, anda masuk ke jurang / blank 45");
                selesai = true;
                break;
            }

            // UPDATE POSISI
            x = nx;
            y = ny;

            // TAMPILKAN POSISI
            System.out.println(
                    "Posisi : (" + y + "," + x + ") -> " + map[y][x]
            );

            // CEK TENAGA
            if (tenaga <= 0) {

                System.out.println("Jalur anda benar, tapi tenaga anda tidak akan kuat, coba jalur lain atau sempatkan istirahat terlebih dahulu");
                selesai = true;
                break;
            }

            // CEK PUNCAK
            if (map[y][x] == 'P') {

                System.out.println("Selamat Pendakian anda berhasil mencapai Puncak Mahameru");
                System.out.println("Sisa tenaga anda : " + tenaga);

                selesai = true;
                break;
            }
        }

        input.close();
    }
}