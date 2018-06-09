package abalone;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Tablero extends javax.swing.JFrame {

    public static javax.swing.JButton campos[][];
    ArrayList<String> datos;
    private int tablero[][];
    private String linea[];
    Angulo angulo = new Angulo();
    private int presX;
    private int presY;
    private int a45;
    private int a135;
    private int fuerablanco;
    private int fueranegro;
    private final ImageIcon imgblanco = new javax.swing.ImageIcon(getClass().getResource("/images/blanco.png"));
    private final ImageIcon imgnegro = new javax.swing.ImageIcon(getClass().getResource("/images/negro.png"));
    private final ImageIcon imgvacio = new javax.swing.ImageIcon(getClass().getResource("/images/cafe.png"));
    boolean presiono = false;
    private Movimiento m;
    private int jugadas=1;
    private String posiciones[][];

    public Tablero() {
        initComponents();
    }

    public void iniciarCampos() {
        panelCampos.removeAll();
        panelCampos.repaint();
        panelCampos.setLayout(new java.awt.GridLayout(11, 19));
        panelCampos.setBackground(new java.awt.Color(111, 37, 3));
        campos = new javax.swing.JButton[11][19];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 19; j++) {
                campos[i][j] = new javax.swing.JButton();
                panelCampos.add(campos[i][j]);
                campos[i][j].setActionCommand(i + "-" + j);

                campos[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        eventosBotones(ev);
                    }
                });
            }
            validate();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCampos = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        menuCargar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maraton de robots");

        panelCampos.setMinimumSize(new java.awt.Dimension(0, 600));
        panelCampos.setPreferredSize(new java.awt.Dimension(699, 0));

        javax.swing.GroupLayout panelCamposLayout = new javax.swing.GroupLayout(panelCampos);
        panelCampos.setLayout(panelCamposLayout);
        panelCamposLayout.setHorizontalGroup(
            panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
        );
        panelCamposLayout.setVerticalGroup(
            panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        menuCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/upload.png"))); // NOI18N
        menuCargar.setText("Cargar Archivo");
        menuCargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCargarMouseClicked(evt);
            }
        });
        menuBar.add(menuCargar);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuCargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCargarMouseClicked
        CargarArchivo ca = new CargarArchivo();
        datos = ca.CargarArchivo();

        if (datos.size() > 0) {
            int x = 0, y = 1;
            tablero = new int[11][19];

            for (int i = 0; i < 11; i++) {
                linea = datos.get(i).split(" ");
                for (int j = 0; j < linea.length; j++) {
                    tablero[i][j] = Integer.parseInt(linea[j]);
                }

            }

            iniciarCampos();
            mostrarTablero();
        }
        Posiciones p=new Posiciones(tablero);
        posiciones=p.coordenadasPosiciones();
    }//GEN-LAST:event_menuCargarMouseClicked
    public void mostrarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == 0) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cafe.png")));
                    campos[i][j].setBackground(new java.awt.Color(111, 37, 3));
                    campos[i][j].setBorderPainted(false);
                }
                if (tablero[i][j] == 1) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blanco.png")));
                    campos[i][j].setBackground(new java.awt.Color(111, 37, 3));
                    campos[i][j].setBorderPainted(false);
                }
                if (tablero[i][j] == 2) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negro.png")));
                    campos[i][j].setBackground(new java.awt.Color(111, 37, 3));
                    campos[i][j].setBorderPainted(false);
                }
                if (tablero[i][j] == 3) {
                    campos[i][j].setVisible(false);
                }

                //////////////////////////////////////////////////////////////////////////////Otro cuento
                if (tablero[i][j] == 4) {
                    campos[i][j].setVisible(true);
                    campos[i][j].setBackground(new Color(238, 238, 238));
                    campos[i][j].setBorder(null);
                    campos[i][j].setEnabled(false);
                }
                if (tablero[i][j] == 5) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borde.png")));
                    campos[i][j].setOpaque(false);
                    campos[i][j].setContentAreaFilled(false);
                    campos[i][j].setBorderPainted(false);
                }
                if (tablero[i][j] == 6) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borde2.png")));
                    campos[i][j].setOpaque(false);
                    campos[i][j].setContentAreaFilled(false);
                    campos[i][j].setBorderPainted(false);
                }
                if (tablero[i][j] == 7) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borde3.png")));
                    campos[i][j].setOpaque(false);
                    campos[i][j].setContentAreaFilled(false);
                    campos[i][j].setBorderPainted(false);
                }
                if (tablero[i][j] == 8) {
                    campos[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borde4.png")));
                    campos[i][j].setOpaque(false);
                    campos[i][j].setContentAreaFilled(false);
                    campos[i][j].setBorderPainted(false);
                }
            }

        }
    }

    public void eventosBotones(ActionEvent ev) {
        String coordenadas[] = ev.getActionCommand().split("-");
        int posX = Integer.parseInt(coordenadas[0]);
        int posY = Integer.parseInt(coordenadas[1]);
        int angulo45 = angulo.getAngulo45(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]));
        int angulo135 = angulo.getAngulo135(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]));
        System.out.println("posX " + posX);
        System.out.println("posY " + posY);
        System.out.println("angullo45 " + angulo45);
        System.out.println("angluo135 " + angulo135);

        //Condicionales para mover en 45°, 135° u horizontal
        if (tablero[posX][posY] == 0 && presX != 0 && presY != 0 /*&& presiono*/) {
            m=new Movimiento(tablero, presX, presY);
            //presiono = false;
            //Mover 1 ficha 45° ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            if ((angulo45 == a45 && (m.adyacencia45Positivo(posX, posY, presX, presY, 1) || m.adyacencia45Negativo(posX, posY, presX, presY, 1)))) {
                m.moverFicha(posX, posY, presX, presY, 2, tablero);
            } //Mover 2 fichas 45° ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            else if ((angulo45 == a45 && (m.adyacencia45Positivo(posX, posY, presX, presY, 2) || m.adyacencia45Negativo(posX, posY, presX, presY, 2)))) {
                m.moverFicha(posX, posY, presX, presY, 2, tablero);
            }//Mover 3 fichas 45° ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            else if ((angulo45 == a45 && (m.adyacencia45Positivo(posX, posY, presX, presY, 3) || m.adyacencia45Negativo(posX, posY, presX, presY, 3)))) {
                m.moverFicha(posX, posY, presX, presY, 2, tablero);
            }//Mover 1 ficha 135° ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            else if ((angulo135 == a135 && (m.adyacencia135Positivo(posX, posY, presX, presY, 1) || m.adyacencia135Negativo(posX, posY, presX, presY, 1)))) {
                m.moverFicha(posX, posY, presX, presY, 2, tablero);
            }//Mover 2 fichas 135° ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            else if ((angulo135 == a135 && (m.adyacencia135Positivo(posX, posY, presX, presY, 2) || m.adyacencia135Negativo(posX, posY, presX, presY, 2)))) {
                m.moverFicha(posX, posY, presX, presY, 2, tablero);
            }//Mover 3 fichas 135° ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 
            else if ((angulo135 == a135 && (m.adyacencia135Positivo(posX, posY, presX, presY, 3) || m.adyacencia135Negativo(posX, posY, presX, presY, 3)))) {
                m.moverFicha(posX, posY, presX, presY, 2, tablero);
            }//Mover 1 ficha horizontalmente ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            else if ((m.adyacenciaXPositivo(posX, posY, presX, presY, 1) || m.adyacenciaXNegativo(posX, posY, presX, presY, 1))) {
                m.moverFicha(posX, posY, presX, presY, 2, tablero);
            }//Mover 2 fichas horizontalmente ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 
            else if ((m.adyacenciaXPositivo(posX, posY, presX, presY, 2) || m.adyacenciaXNegativo(posX, posY, presX, presY, 2))) {
                m.moverFicha(posX, posY, presX, presY, 2, tablero);
            }//Mover 3 fichas horizontalmente ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 
            else if ((m.adyacenciaXPositivo(posX, posY, presX, presY, 3) || m.adyacenciaXNegativo(posX, posY, presX, presY, 3))) {
                m.moverFicha(posX, posY, presX, presY, 2, tablero);
            }
            System.out.println(jugadas+"." +posiciones[presX][presY]+"-"+posiciones[posX][posY]);
            presX = 0;
            presY = 0;
            Minimmax mm=new Minimmax(tablero);
            jugadas++;
            tablero=mm.generarArbol(1, 2,jugadas);
            
            //mm.imprimirTablero();
            
        }

        //Condicionales para empujar rivales en 45°, 135° u horizontal
        if (tablero[posX][posY] == 1 && presX != 0 && presY != 0 /*&& presiono*/) {
            //presiono = false;
            //Empujar 1 ficha en 45° positivo ↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗
            /*if ((angulo45 == a45 && m.m.adyacencia45Positivo(posX, posY, presX, presY, 1) && cantidadRivales45(posX, posY, 1) < 1)) {
                fuerablanco += m.empujarRival(posX, posY, posX, posY, 2, 1, imgnegro, imgblanco);
            }*/
            
            
           

            //Empujar 2 fichas en 45° positivo ↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗
            if ((angulo45 == a45 && m.adyacencia45Positivo(posX, posY, presX, presY, 2) && m.cantidadRivales45(posX, posY, 1) < 2)) {
                fuerablanco += m.empujarRival(posX, posY, posX + 1, posY - 1, 2, 1, tablero);
            }//Empujar 2 fichas en 45° negativo ↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘
            else if (angulo45 == a45 && m.adyacencia45Negativo(posX, posY, presX, presY, 2) && m.cantidadRivales45(posX, posY, 1) < 2) {
                fuerablanco += m.empujarRival(posX, posY, posX - 1, posY + 1, 2, 1, tablero);
            }//Empujar 3 fichas en 45° negativo ↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗
            else if ((angulo45 == a45 && (m.adyacencia45Positivo(posX, posY, presX, presY, 3)) && m.cantidadRivales45(posX, posY, 1) < 3)) {
                if (m.cantidadRivales45(posX, posY, 1) == 2) {
                    fuerablanco += m.empujarRival(posX, posY, posX + 2, posY - 2, 2, 1, tablero);
                } else {
                    fuerablanco += m.empujarRival(posX, posY, posX + 1, posY - 1, 2, 1, tablero);
                }

            }//Empujar 3 fichas en 45° negativo ↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘
            else if ((angulo45 == a45 && (m.adyacencia45Negativo(posX, posY, presX, presY, 3)) && m.cantidadRivales45(posX, posY, 1) < 3)) {
                if (m.cantidadRivales45(posX, posY, 1) == 2) {
                    fuerablanco += m.empujarRival(posX, posY, posX - 2, posY + 2, 2, 1, tablero);
                } else {
                    fuerablanco += m.empujarRival(posX, posY, posX - 1, posY + 1, 2, 1, tablero);
                }

            }//Empujar 2 fichas en 135° positivo ↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗
            else if ((angulo135 == a135 && (m.adyacencia135Positivo(posX, posY, presX, presY, 2) && m.cantidadRivales135(posX, posY, 1) < 2))) {
                fuerablanco += m.empujarRival(posX, posY, posX - 1, posY - 1, 2, 1, tablero);
            }//Empujar 2 fichas en 135° negativo ↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘
            else if ((angulo135 == a135 && m.adyacencia135Negativo(posX, posY, presX, presY, 2) && m.cantidadRivales135(posX, posY, 1) < 2)) {
                fuerablanco += m.empujarRival(posX, posY, posX + 1, posY + 1, 2, 1, tablero);
            }//Empujar 3 fichas en 135° positivo ↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗↗
            else if ((angulo135 == a135 && (m.adyacencia135Positivo(posX, posY, presX, presY, 3) && m.cantidadRivales135(posX, posY, 1) < 3))) {
                if (m.cantidadRivales135(posX, posY, 1) == 2) {
                    fuerablanco += m.empujarRival(posX, posY, posX - 2, posY - 2, 2, 1, tablero);
                } else {
                    fuerablanco += m.empujarRival(posX, posY, posX - 1, posY - 1, 2, 1, tablero);
                }

            }//Empujar 3 fichas en 135° negativo ↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘↘
            else if ((angulo135 == a135 && m.adyacencia135Negativo(posX, posY, presX, presY, 3) && m.cantidadRivales135(posX, posY, 1) < 3)) {
                if (m.cantidadRivales135(posX, posY, 1) == 2) {
                    fuerablanco += m.empujarRival(posX, posY, posX + 2, posY + 2, 2, 1, tablero);
                } else {
                    fuerablanco += m.empujarRival(posX, posY, posX + 1, posY + 1, 2, 1, tablero);
                }

            }//Empujar 2 fichas horizonalmente positivo →→→→→→→→→→→→→→→→→→→→
            else if (m.adyacenciaXPositivo(posX, posY, presX, presY, 2) && m.cantidadRivalesX(posX, posY, 1) < 2) {
                fuerablanco += m.empujarRival(posX, posY, posX, posY - 2, 2, 1, tablero);
            }//Empujar 2 fichas horizonalmente negativo ←←←←←←←←←←←←←←←←←←←←
            else if ((m.adyacenciaXNegativo(posX, posY, presX, presY, 2) && m.cantidadRivalesX(posX, posY, 1) < 2)) {
                fuerablanco += m.empujarRival(posX, posY, posX, posY + 2, 2, 1, tablero);
            }//Empujar 3 fichas horizonalmente positivo →→→→→→→→→→→→→→→→→→→→
            else if (((m.adyacenciaXPositivo(posX, posY, presX, presY, 3) && m.cantidadRivalesX(posX, posY, 1) < 3))) {
                if (m.cantidadRivalesX(posX, posY, 1) == 2) {
                    fuerablanco += m.empujarRival(posX, posY, posX, posY - 4, 2, 1, tablero);
                } else {
                    fuerablanco += m.empujarRival(posX, posY, posX, posY - 2, 2, 1, tablero);
                }
                
            }//Empujar 3 fichas horizonalmente negativo ←←←←←←←←←←←←←←←←←←←←
            else if ((m.adyacenciaXNegativo(posX, posY, presX, presY, 3) && m.cantidadRivalesX(posX, posY, 1) < 3)) {
                if (m.cantidadRivalesX(posX, posY, 1) == 2) {
                    fuerablanco += m.empujarRival(posX, posY, posX, posY + 4, 2, 1, tablero);
                } else {
                    fuerablanco += m.empujarRival(posX, posY, posX, posY + 2, 2, 1, tablero);
                }
                
            }
            presX = 0;
            presY = 0;
        } else if (tablero[posX][posY] == 2 /*&& !presiono*/) {//Marcar un presionado
            campos[posX][posY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negroPress.png")));
            presX = Integer.parseInt(coordenadas[0]);
            presY = Integer.parseInt(coordenadas[1]);
            a45 = angulo.getAngulo45(posX, posY);
            a135 = angulo.getAngulo135(posX, posY);
            //presiono = true;
        } else if (tablero[presX][presY] == 2 /*&& presiono*/) {//Si vuelven a presionar uno que ya estaba presionado, lo desmarca (no lo movera)
            campos[presX][presY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negro.png")));
            //presiono = false;
        }
        System.out.println("--");
        System.out.println("presX " + presX);
        System.out.println("presY " + presY);
        System.out.println("a45 " + a45);
        System.out.println("a135 " + a135);
        System.out.println("||||||||||||||||");
        mostrarTablero();
        if (fuerablanco==6) {
            JOptionPane.showMessageDialog(this, "Has ganado");
        }

    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCargar;
    private javax.swing.JPanel panelCampos;
    // End of variables declaration//GEN-END:variables
}
