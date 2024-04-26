package GUI;

import java.awt.*;

public class MyGridBagConstraints {

    public GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int gap = 3;
        gbc.insets = new Insets(gap, gap + 2 * gap * x, gap, gap);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0.5;
        return gbc;
    }
}
