package boblovespihonkaimod.actions;

import boblovespihonkaimod.cards.attacks.HonkaiLance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DiscardSkillAddHonkaiLanceAction extends AbstractGameAction
{
	public DiscardSkillAddHonkaiLanceAction(AbstractCreature source)
	{
		this.source = source;
		this.duration = Settings.ACTION_DUR_FAST;
	}

	public void update()
	{
		if (duration == Settings.ACTION_DUR_FAST)
		{
			for (AbstractCard c : AbstractDungeon.player.hand.group)
			{
				if (c.type != AbstractCard.CardType.ATTACK)
				{
					addToTop(new MakeTempCardInHandAction(new HonkaiLance()));
					addToTop(new DiscardSpecificCardAction(c));
				}
			}
			isDone = true;
		}
	}
}
