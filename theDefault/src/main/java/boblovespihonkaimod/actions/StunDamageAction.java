package boblovespihonkaimod.actions;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.function.Predicate;

public class StunDamageAction extends AbstractGameAction
{
	private DamageInfo info;
	private AttackEffect effect;
	private float chance;
	private Predicate<AbstractMonster.Intent> when;

	public StunDamageAction(AbstractMonster target, DamageInfo info, AttackEffect effect, float chance, Predicate<AbstractMonster.Intent> when)
	{
		this.info = info;
		this.effect = effect;
		this.chance = chance;
		this.when = when;
		setValues(target, info);
		actionType = AbstractGameAction.ActionType.DAMAGE;
		startDuration = Settings.ACTION_DUR_FAST;
		duration = startDuration;
	}

	public void update()
	{
		if (shouldCancelAction())
		{
			isDone = true;
		} else
		{
			tickDuration();
			if (isDone)
			{
				AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, effect, false));
				target.damage(info);
				if (target.lastDamageTaken > 0 && !target.hasPower(StunMonsterPower.POWER_ID) && when.test(((AbstractMonster)target).intent))
				{
					if (AbstractDungeon.miscRng.random() < chance)
					{
						addToTop(new ApplyPowerAction(target, source, new StunMonsterPower((AbstractMonster) target)));
						addToTop(new WaitAction(0.1F));
					}
				}

				if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
				{
					AbstractDungeon.actionManager.clearPostCombatActions();
				}
			}
		}
	}
}
