package gui.menu;

import gui.view.ViewHub;
import gui.view.ViewOption;
import languagePack.LanguagePackContainer;

import javax.swing.*;

class ViewsMenu extends JMenu{
    ViewHub viewHub;

    ViewsMenu(ViewHub viewHub){
        super(LanguagePackContainer.getLanguagePack().getViewsMenuHeader());
        this.viewHub = viewHub;

        JMenuItem setSelectionView = new JMenuItem(LanguagePackContainer.getLanguagePack().getViewsMenuSetSelectionView());
        setSelectionView.setToolTipText(LanguagePackContainer.getLanguagePack().getViewsMenuSetSelectionViewToolTipText());
        setSelectionView.addActionListener( action ->{
            viewHub.setViewModeTo(ViewOption.SELECT_VIEW);
        });
        this.add(setSelectionView);

        JMenuItem setPictureView = new JMenuItem(LanguagePackContainer.getLanguagePack().getViewsMenuSetPictureView());
        setPictureView.setToolTipText(LanguagePackContainer.getLanguagePack().getViewsMenuSetPictureViewToolTipText());
        setPictureView.addActionListener(action -> {
            viewHub.setViewModeTo(ViewOption.PICTURE_VIEW);
        });
        this.add(setPictureView);

        JMenuItem setTableView = new JMenuItem(LanguagePackContainer.getLanguagePack().getViewsMenuSetTableView());
        setTableView.setToolTipText(LanguagePackContainer.getLanguagePack().getViewsMenuSetTableViewToolTip());
        setTableView.addActionListener(action -> {
            viewHub.setViewModeTo(ViewOption.TABLE_VIEW);
        });
        this.add(setTableView);
    }
}
