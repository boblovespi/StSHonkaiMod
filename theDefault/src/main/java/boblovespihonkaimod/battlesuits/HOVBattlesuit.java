package boblovespihonkaimod.battlesuits;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.actions.DoubleHonkaiAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;

public class HOVBattlesuit extends AbstractBattlesuit
{
	public static String ID = DefaultMod.makeID("hov");
	private static final StanceStrings strings = CardCrawlGame.languagePack.getStanceString(ID);

	public HOVBattlesuit()
	{
		super.ID = ID;
		name = strings.NAME;
	}

	@Override
	public void onUltUse()
	{
		// todo: figure out
	}

	@Override
	public void atStartOfTurn()
	{
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(2));
		AbstractDungeon.actionManager.addToBottom(new DoubleHonkaiAction(AbstractDungeon.player, AbstractDungeon.player));
	}

	@Override
	public void onExitOrVictory()
	{
	}

	@Override
	public void update()
	{
		super.update();
	}

	@Override
	public void updateDescription()
	{
		description = strings.DESCRIPTION[0];
	}

	@Override
	public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card)
	{
		return damage;
	}
}
