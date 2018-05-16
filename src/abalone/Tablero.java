package abalone;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        System.out.println("posX "+posX);
        System.out.println("posY "+posY);
        System.out.println("angullo45 "+angulo45);
        System.out.println("angluo135 "+angulo135);

        //Movimiento naranja (diagonal)
        if (tablero[posX][posY] == 0 && (angulo45 == a45 && adyacencia45(posX, posY, presX, presY))) {
            tablero[posX][posY] = 2;
            campos[posX][posY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negro.png")));
            tablero[presX][presY] = 0;
            campos[presX][presY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cafe.png")));
        } else if (tablero[posX][posY] == 0 && (angulo135 == a135 && adyacencia135(posX, posY, presX, presY))) {
            tablero[posX][posY] = 2;
            campos[posX][posY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negro.png")));
            tablero[presX][presY] = 0;
            campos[presX][presY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cafe.png")));
        } else if (tablero[posX][posY] == 0 && adyacenciaX(posX, posY, presX, presY)) {
            tablero[posX][posY] = 2;
            campos[posX][posY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negro.png")));
            tablero[presX][presY] = 0;
            campos[presX][presY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cafe.png")));
        }

        //
        else if (tablero[posX][posY] == 2) {
            campos[presX][presY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negroPress.png")));
            
            presX = Integer.parseInt(coordenadas[0]);
            presY = Integer.parseInt(coordenadas[1]);
            a45 = angulo.getAngulo45(posX, posY);
            a135 = angulo.getAngulo135(posX, posY);
        }
        else if (tablero[presX][presY] == 2) {
            campos[presX][presY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negro.png")));
        }
        System.out.println("--");
        System.out.println("presX "+presX);
        System.out.println("presY "+presY);
        System.out.println("a45 "+a45);
        System.out.println("a135 "+a135);
        System.out.println("");

    }

    public boolean adyacencia135(int nuevox, int nuevoy, int viejox, int viejoy) {
        return (nuevox - 1 == viejox && nuevoy - 1 == viejoy) || (nuevox + 1 == viejox && nuevoy + 1 == viejoy);

    }

    public boolean adyacencia45(int nuevox, int nuevoy, int viejox, int viejoy) {
        return (nuevox - 1 == viejox && nuevoy + 1 == viejoy) || (nuevox + 1 == viejox && nuevoy - 1 == viejoy);

    }

    public boolean adyacenciaX(int nuevox, int nuevoy, int viejox, int viejoy) {
        return (nuevox  == viejox && nuevoy-2 == viejoy) || (nuevox == viejox && nuevoy+2 == viejoy);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCargar;
    private javax.swing.JPanel panelCampos;
    // End of variables declaration//GEN-END:variables
}
