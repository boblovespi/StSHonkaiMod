package boblovespihonkaimod.battlesuits;

import com.megacrit.cardcrawl.stances.AbstractStance;

public abstract class AbstractBattlesuit extends AbstractStance
{
	public abstract void onUltUse();

	public void onExitOrVictory()
	{
	}

	@Override
	public void onExitStance()
	{
		onExitOrVictory();
	}
}
