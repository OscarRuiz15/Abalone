package abalone;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.html.MinimalHTMLWriter;

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
    private final Movimiento mv = new Movimiento();
    private int jugadas = 1;
    private String posiciones[][];
    private final int ficha = 2;
    private final int fichacontraria = 1;
    String historial = "Persona" + "\t" + "Maquina" + "\n";

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tajugadas = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        menuCargar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Abalone");

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

        tajugadas.setEditable(false);
        tajugadas.setColumns(20);
        tajugadas.setRows(5);
        jScrollPane1.setViewportView(tajugadas);

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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
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
        Posiciones p = new Posiciones(tablero);
        posiciones = p.coordenadasPosiciones();
    }//GEN-LAST:event_menuCargarMouseClicked
    public void mostrarTablero() {
        int xd = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                try {
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
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(null, "Ganaste kappa");
                    xd = 1;
                    break;
                }
            }
            if (xd == 1) {
                break;
            }
        }
    }

    private int[] cantidadFichas(int ficha, int fichacontraria) {
        int cantidad[] = new int[2];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == ficha) {
                    cantidad[0]++;
                }
                if (tablero[i][j] == fichacontraria) {
                    cantidad[1]++;
                }
            }
        }

        return cantidad;
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

        List<Integer[]> moves = new ArrayList<>();

        if (tablero[posX][posY] == 2 /*&& !presiono*/) {//Marcar un presionado
            campos[posX][posY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negroPress.png")));
            presX = Integer.parseInt(coordenadas[0]);
            presY = Integer.parseInt(coordenadas[1]);
            a45 = angulo.getAngulo45(posX, posY);
            a135 = angulo.getAngulo135(posX, posY);
        } else if (tablero[posX][posY] < 2 /*&& !presiono*/) {

            if (tablero[posX][posY] == 1) {
                System.out.println("");
            }

            //Verificar movimiento en angulo 45 positivo
            Integer movimientos[] = mv.posiblesMovimientos(tablero, 0, ficha, fichacontraria, presX, presY, -1, 1);
            if (movimientos[4] != 1) {
                moves.add(movimientos);
            }

            //Verificar movimiento en angulo 45 negativo
            movimientos = mv.posiblesMovimientos(tablero, 0, ficha, fichacontraria, presX, presY, 1, -1);
            if (movimientos[4] != 1) {
                moves.add(movimientos);
            }

            //Verificar movimiento en angulo 135 negativo
            movimientos = mv.posiblesMovimientos(tablero, 0, ficha, fichacontraria, presX, presY, 1, 1);
            if (movimientos[4] != 1) {
                moves.add(movimientos);
            }
            //Verificar movimiento en angulo 135 positivo
            movimientos = mv.posiblesMovimientos(tablero, 0, ficha, fichacontraria, presX, presY, -1, -1);
            if (movimientos[4] != 1) {
                moves.add(movimientos);
            }
            //Verificar movimiento en angulo X positivo
            movimientos = mv.posiblesMovimientos(tablero, 0, ficha, fichacontraria, presX, presY, 0, 2);
            if (movimientos[4] != 1) {
                moves.add(movimientos);
            }
            //Verificar movimiento en angulo X negativo
            movimientos = mv.posiblesMovimientos(tablero, 0, ficha, fichacontraria, presX, presY, 0, -2);
            if (movimientos[4] != 1) {
                moves.add(movimientos);
            }
            //Si la lista de movimientos esta vacia, pues nada
            if (moves.isEmpty()) {
                campos[presX][presY].setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negro.png")));
            } //Si hay empujes disponibles
            else {
                //Inicializa la clase Minimax
                Minimmax mm = new Minimmax(tablero, posiciones);

                //Recorre los movimientos
                for (int i = 0; i < moves.size(); i++) {
                    Integer[] m = moves.get(i);

                    /*System.out.println("\nMovimientos: ");
                     for (int j = 0; j < m.length; j++) {
                     System.out.print("m[" + j + "]=" + m[j] + " ");
                     }

                     System.out.println("\nRecordar -> posX=" + posX + " posY=" + posY);*/
                    //Si el valor X de donde me podria mover es igual a donde me quiero a mover
                    //Si el valor Y de donde me podria mover es igual a donde me quiero a mover
                    if (m[0] == posX && m[1] == posY && m[2] == 0 && m[3] == 0) {
                        //Actualiza el tablero con el valor de mi ficha (2)
                        tablero[posX][posY] = ficha;
                        //Actualiza el tablero en mi posicion anterior con 0 (libre)
                        tablero[presX][presY] = 0;

                        String jug = posiciones[presX][presY] + "-" + posiciones[posX][posY];
                        tablero = mm.generarArbol(fichacontraria, ficha, jugadas);
                        jugadas++;

                        historial += jug + "\t" + mm.jugada + "\n";
                        System.out.println(historial);
                        tajugadas.setText(historial);

                    } else if (m[0] == posX && m[1] == posY && m[2] != 0 && m[3] != 0) {
                        tablero[posX][posY] = ficha;
                        tablero[m[2]][m[3]] = fichacontraria;

                        tablero[presX][presY] = 0;
                        String jug = posiciones[presX][presY] + "-" + posiciones[posX][posY];
                        tablero = mm.generarArbol(fichacontraria, ficha, jugadas);
                        historial += " " + "\t" + jug + "\t" + mm.jugada + "\n";
                        System.out.println(historial);
                        tajugadas.setText(historial);
                    }

                }

            }
        }

        System.out.println("\n--");
        System.out.println("presX " + presX);
        System.out.println("presY " + presY);
        System.out.println("a45 " + a45);
        System.out.println("a135 " + a135);
        System.out.println("=============================");

        int idk[] = cantidadFichas(1, 2);
        if (idk[0] == 6) {
            JOptionPane.showMessageDialog(null, "Has ganado");
        }
        if (idk[1] == 6) {
            JOptionPane.showMessageDialog(null, "Gana IA");
        }
        mostrarTablero();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCargar;
    private javax.swing.JPanel panelCampos;
    private javax.swing.JTextArea tajugadas;
    // End of variables declaration//GEN-END:variables
}
