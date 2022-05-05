package boblovespihonkaimod.util;

import basemod.abstracts.AbstractCardModifier;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FreeIfCertainCostMod extends AbstractCardModifier
{
	public static String ID = DefaultMod.makeID("blockMod");
	private int min;
	private int baseCost;

	public FreeIfCertainCostMod(int min, int baseCost)
	{
		this.min = min;
		this.baseCost = baseCost;
	}

	@Override
	public AbstractCardModifier makeCopy()
	{
		return new FreeIfCertainCostMod(min, baseCost);
	}

	@Override
	public boolean isInherent(AbstractCard card)
	{
		return true;
	}

	@Override
	public void onUpdate(AbstractCard card)
	{
		int honkai = 0;
		if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(HonkaiPower.id))
			honkai = AbstractDungeon.player.getPower(HonkaiPower.id).amount;
		if (honkai >= min)
			card.costForTurn = 0;
		else
			card.costForTurn = baseCost;
	}

	@Override
	public void onInitialApplication(AbstractCard card)
	{
		baseCost = card.cost;
	}
}
