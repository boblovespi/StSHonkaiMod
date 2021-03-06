package boblovespihonkaimod.battlesuits;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.actions.DiscardSkillAddHonkaiLanceAction;
import boblovespihonkaimod.actions.DoubleHonkaiAction;
import boblovespihonkaimod.cards.HonkaiEnergyStatus;
import boblovespihonkaimod.cards.attacks.HonkaiLance;
import boblovespihonkaimod.powers.HonkaiPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class VoidDrifterBattlesuit extends AbstractBattlesuit
{
	public static String ID = DefaultMod.makeID("voidDrifter");
	private static final StanceStrings strings = CardCrawlGame.languagePack.getStanceString(ID);

	public VoidDrifterBattlesuit()
	{
		super.ID = ID;
		name = strings.NAME;
		ultEnergyCost = 2;
	}

	@Override
	public void onUltUse()
	{
		AbstractDungeon.actionManager.addToBottom(new DoubleHonkaiAction(AbstractDungeon.player, AbstractDungeon.player));
		AbstractDungeon.actionManager.addToBottom(new DiscardSkillAddHonkaiLanceAction(AbstractDungeon.player));
		for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters)
		{
			AbstractDungeon.actionManager.addToBottom(new StunMonsterAction(mon, AbstractDungeon.player));
		}
	}

	@Override
	public void updateDescription()
	{
		description = strings.DESCRIPTION[0];
	}

	@Override
	public void onEnterStance()
	{

	}

	@Override
	public void atStartOfTurn()
	{
		super.atStartOfTurn();
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new HonkaiEnergyStatus(), 1, true, true));
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new HonkaiLance()));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HonkaiPower(AbstractDungeon.player, AbstractDungeon.player, 2)));
	}

	@Override
	public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card)
	{
		int add = 0;
		if (AbstractDungeon.player.hasPower(HonkaiPower.id))
			add = AbstractDungeon.player.getPower(HonkaiPower.id).amount;
		return damage + add;
	}

	@Override
	public float atDamageReceive(float damage, DamageInfo.DamageType damageType)
	{
		int add = 0;
		if (AbstractDungeon.player.hasPower(HonkaiPower.id))
			add = AbstractDungeon.player.getPower(HonkaiPower.id).amount;
		return damage + add;
	}

	@Override
	public void onPlayCard(AbstractCard card)
	{
		super.onPlayCard(card);
	}
}
