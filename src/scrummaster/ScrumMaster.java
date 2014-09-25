package scrummaster;

import java.util.Random;
import scrummaster.display.DisplayJFrame;
import scrummaster.randomize.RandomCandidateGenerator;
import scrummaster.randomize.ProbabilityGenerator;

/**
 *
 * @author Nick
 */
public class ScrumMaster
{

    private final Candidate finalCandidate = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {

        final History history = new History();
        if (Constants.DEBUG)
        {
            System.out.println("History: " + history.toString());
        }

        final ProbabilityGenerator probabilityGen = new ProbabilityGenerator(history);
        final Random random = new Random(System.currentTimeMillis());
        final RandomCandidateGenerator candidateGenerator = new RandomCandidateGenerator(
                probabilityGen, random);

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(DisplayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DisplayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DisplayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DisplayJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new DisplayJFrame(candidateGenerator, random).setVisible(true);
            }
        });

    }
}
