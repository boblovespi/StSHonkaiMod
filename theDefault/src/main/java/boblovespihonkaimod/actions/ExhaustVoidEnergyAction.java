package boblovespihonkaimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class ExhaustVoidEnergyAction extends AbstractGameAction
{
	private static final UIStrings uiString = CardCrawlGame.languagePack.getUIString("ExhaustAction");
	public static final String[] TEXT = uiString.TEXT;
	public static int numExhausted;
	private AbstractPlayer p;

	public ExhaustVoidEnergyAction(AbstractPlayer p, int amount)
	{
		this.p = p;
		this.amount = amount;
		duration = startDuration = Settings.ACTION_DUR_FAST;
		actionType = ActionType.EXHAUST;
	}

	@Override
	public void update()
	{
		if (duration == startDuration)
		{
			if (p.hand.size() == 0)
			{
				isDone = true;
				return;
			}

			int i;
			if (p.hand.size() <= amount)
			{
				amount = p.hand.size();
				numExhausted = amount;
				i = p.hand.size();

				for (int j = 0; j < i; ++j)
				{
					AbstractCard c = p.hand.getTopCard();
					if (c instanceof VoidCard)
						addToBot(new GainEnergyAction(1));
					p.hand.moveToExhaustPile(c);
				}

				CardCrawlGame.dungeon.checkForPactAchievement();
				return;
			}

			numExhausted = amount;
			AbstractDungeon.handCardSelectScreen.open(TEXT[0], amount, false, false);
			tickDuration();
			return;

		}

		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
		{
			for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group)
			{
				System.out.println(c);
				if (c instanceof VoidCard)
					addToBot(new GainEnergyAction(2));
				p.hand.moveToExhaustPile(c);
			}

			CardCrawlGame.dungeon.checkForPactAchievement();
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}

		tickDuration();
	}
}
