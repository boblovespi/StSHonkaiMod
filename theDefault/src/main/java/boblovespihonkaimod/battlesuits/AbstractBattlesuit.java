package boblovespihonkaimod.battlesuits;

import boblovespihonkaimod.powers.HonkaiPower;
import com.evacipated.cardcrawl.mod.stslib.patches.HitboxRightClick;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;

public abstract class AbstractBattlesuit extends AbstractStance
{
	private boolean isPlayerTurn = true;
	private boolean hasUsedUlt = false;
	protected int ultEnergyCost = 0;
	protected int ultHonkaiCost = 0;

	public abstract void onUltUse();

	@Override
	public void atStartOfTurn()
	{
		isPlayerTurn = true;
	}

	@Override
	public void onEndOfTurn()
	{
		isPlayerTurn = false;
	}

	public void onExitOrVictory()
	{
	}

	@Override
	public void onExitStance()
	{
		onExitOrVictory();
	}

	@Override
	public void update()
	{
		int honkai = AbstractDungeon.player.hasPower(HonkaiPower.id) ? AbstractDungeon.player.getPower(HonkaiPower.id).amount : 0;
		if (isPlayerTurn && !hasUsedUlt && AbstractDungeon.player.energy.energy >= ultEnergyCost && honkai >= ultHonkaiCost && HitboxRightClick.rightClicked.get(AbstractDungeon.player.hb))
		{
			hasUsedUlt = true;
			AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(ultEnergyCost));
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, HonkaiPower.id, ultHonkaiCost));
			onUltUse();
		}
	}
}
