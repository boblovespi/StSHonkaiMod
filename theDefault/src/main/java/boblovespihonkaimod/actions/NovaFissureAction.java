package boblovespihonkaimod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class NovaFissureAction extends AbstractGameAction
{
	private DamageInfo info;
	private int healAmount;

	public NovaFissureAction(AbstractCreature target, DamageInfo info, int healAmount)
	{
		this.info = info;
		this.healAmount = healAmount;
		setValues(target, info);
		actionType = AbstractGameAction.ActionType.DAMAGE;
		duration = Settings.ACTION_DUR_MED;
	}

	public void update()
	{
		if (duration == Settings.ACTION_DUR_MED && target != null)
		{
			AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.SHIELD));
			target.damage(info);
			if ((target.isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower("Minion"))
			{
				addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, healAmount));
			}
			if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
			{
				AbstractDungeon.actionManager.clearPostCombatActions();
			}
		}
		tickDuration();
	}
}
