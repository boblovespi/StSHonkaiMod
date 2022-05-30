package boblovespihonkaimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class VoidArchivesAction extends AbstractGameAction
{
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");
	public static final String[] TEXT = uiStrings.TEXT;

	private AbstractPlayer p;

	public VoidArchivesAction(int amount)
	{
		p = AbstractDungeon.player;
		setValues(p, AbstractDungeon.player, amount);
		actionType = ActionType.CARD_MANIPULATION;
		duration = Settings.ACTION_DUR_MED;
	}

	public void update()
	{
		AbstractCard card;
		if (duration == Settings.ACTION_DUR_MED)
		{
			CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
			for (AbstractCard abstractCard : p.drawPile.group)
			{
				card = abstractCard;
				tmp.addToRandomSpot(card);
			}

			if (tmp.size() == 0)
			{
				isDone = true;
			} else if (tmp.size() == 1)
			{
				for (int i = 0; i < amount; i++)
				{
					card = tmp.getTopCard();
					card.unhover();
					card = card.makeStatEquivalentCopy();
					card.upgrade();
					if (p.hand.size() == 10)
					{
						p.discardPile.addToTop(card);
						p.createHandIsFullDialog();
					} else
					{
						card.lighten(true);
						card.setAngle(0.0F);
						card.drawScale = 0.12F;
						card.targetDrawScale = 0.75F;
						card.current_x = CardGroup.DRAW_PILE_X;
						card.current_y = CardGroup.DRAW_PILE_Y;
						AbstractDungeon.player.hand.addToTop(card);
						AbstractDungeon.player.hand.refreshHandLayout();
						AbstractDungeon.player.hand.applyPowers();
					}
				}
				isDone = true;
			} else
			{
				AbstractDungeon.gridSelectScreen.open(tmp, 1, TEXT[0], false);
				tickDuration();
			}
		} else
		{
			if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0)
			{
				for (AbstractCard abstractCard : AbstractDungeon.gridSelectScreen.selectedCards)
				{
					for (int i = 0; i < amount; i++)
					{
						card = abstractCard;
						card.unhover();
						card = card.makeStatEquivalentCopy();
						card.upgrade();
						if (p.hand.size() == 10)
						{
							p.discardPile.addToTop(card);
							p.createHandIsFullDialog();
						} else
						{
							p.hand.addToTop(card);
						}
						p.hand.refreshHandLayout();
						p.hand.applyPowers();
					}
				}

				AbstractDungeon.gridSelectScreen.selectedCards.clear();
				p.hand.refreshHandLayout();
			}

			tickDuration();
		}
	}
}
