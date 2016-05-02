package unlp.info.rInfo.gui;

import unlp.info.rInfo.Programa;
import unlp.info.rInfo.events.ChangeDirectionEvent;
import unlp.info.rInfo.events.ChangePosEvent;
import unlp.info.rInfo.events.StateChangeEvent;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RobotPane extends JPanel {
    private GRobot robot;
    private JLabel pos, estado, bFlores, bPapeles, eFlores, ePapeles;
    private RobotGraph robotGraph;

    public RobotPane(GRobot robot){
        this.robot = robot;
        setPreferredSize(new Dimension(200, 120));
        setSize(188, 120);
        initComponents();
        registrarRobot();

    }

    private void initComponents(){
        setLayout(null);

        robotGraph = new RobotGraph(robot);
        add(robotGraph);
        robotGraph.setBounds(12,20, 20, 20);

        pos = new JLabel("Pos: (" + robot.getPos().x + ", " + robot.getPos().y  + ")");
        add(pos);
        pos.setBounds(44, 20, 100, 20);

        JLabel infRobot = new JLabel("Bolsa");
        JLabel infEsquina = new JLabel("Esquina");
        JLabel[] infBar = { new JLabel("F"), new JLabel("P"), new JLabel("F"), new JLabel("P") };

        infRobot.setHorizontalAlignment(JLabel.CENTER);
        infEsquina.setHorizontalAlignment(JLabel.CENTER);

        add(infRobot);
        infRobot.setBounds(12, 50, 80, 12);
        add(infEsquina);
        infEsquina.setBounds(92, 50, 80, 12);

        for(int i = 0; i < infBar.length; i++){
            infBar[i].setHorizontalAlignment(JLabel.CENTER);
            add(infBar[i]);
            infBar[i].setBounds(12 + (40 * i), 64, 40, 12);
        }

        bFlores = new JLabel("00");
        bPapeles= new JLabel("00");
        eFlores = new JLabel("00");
        ePapeles= new JLabel("00");

        bFlores.setHorizontalAlignment(JLabel.CENTER);
        bPapeles.setHorizontalAlignment(JLabel.CENTER);
        eFlores.setHorizontalAlignment(JLabel.CENTER);
        ePapeles.setHorizontalAlignment(JLabel.CENTER);

        add(bFlores);
        add(bPapeles);
        add(eFlores);
        add(ePapeles);

        bFlores.setBounds(12, 78, 40, 12);
        bPapeles.setBounds(52, 78, 40, 12);
        eFlores.setBounds(92, 78, 40, 12);
        ePapeles.setBounds(132, 78, 40, 12);

        estado = new JLabel("Ejecutando");
        estado.setHorizontalAlignment(JLabel.CENTER);
        add(estado);
        estado.setBounds(0, 95, getWidth(), 12);

    }

    private void registrarRobot(){
        robot.addChangePosListener((GRobot r, ChangePosEvent event)-> {
            pos.setText("Pos: (" + event.getPosAct().x + ", " + event.getPosAct().y + ")");
            int papeles = Programa.getPapeles(event.getPosAct());
            if(papeles < 10){
                ePapeles.setText("0" + papeles);
            }else{
                ePapeles.setText("" + papeles);
            }
            int flores = Programa.getFlores(event.getPosAct());
            if(flores < 10){
                eFlores.setText("0" + flores);
            }else{
                eFlores.setText("" + flores);
            }
        });

        robot.addChangeDirectionListener((GRobot r, ChangeDirectionEvent event) -> robotGraph.repaint());
        
        robot.addChangeStateListener((GRobot r, StateChangeEvent event)->estado.setText(event.getState()));
    }

    class RobotGraph extends JPanel{
        GRobot robot;
        public RobotGraph(GRobot robot){
            this.robot = robot;
        }

        public void paint(Graphics g){
            if(g != null) {
                g.clearRect(0,0,20,20);
                g.setColor(robot.getColor());
                g.fillOval(1, 1, 18, 18);
                g.setColor(new Color(0x333333));
                g.drawOval(1, 1, 18, 18);
                switch (robot.getSentido()) {
                    case GRobot.NORTE:
                        g.fillOval(4, 3, 4, 4);
                        g.fillOval(12, 3, 4, 4);
                        //g.drawLine(x + 5, y, x + 5, y + 5);
                        //g.drawLine(x, y + 5, x + 10, y + 5);
                        break;
                    case GRobot.SUR:
                        g.fillOval(4, 13, 4, 4);
                        g.fillOval(12, 13, 4, 4);
                        break;
                    case GRobot.ESTE:
                        g.fillOval(13, 4, 4, 4);
                        g.fillOval(13, 12, 4, 4);
                        break;
                    case GRobot.OESTE:
                        g.fillOval(3, 4, 4, 4);
                        g.fillOval(3, 12, 4, 4);
                        break;
                }
            }
        }
    }
}
