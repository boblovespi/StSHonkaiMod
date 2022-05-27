package boblovespihonkaimod.actions;

import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class DoubleHonkaiAction extends AbstractGameAction
{
	public DoubleHonkaiAction(AbstractCreature target, AbstractCreature source)
	{
		this.target = target;
		this.source = source;
		actionType = ActionType.DEBUFF;
		attackEffect = AttackEffect.FIRE;
	}

	public void update()
	{
		if (target != null && target.hasPower(HonkaiPower.id))
		{
			addToTop(new ApplyPowerAction(target, source, new HonkaiPower(target, source, target.getPower(HonkaiPower.id).amount), target.getPower(HonkaiPower.id).amount));
		}
		isDone = true;
	}
}
