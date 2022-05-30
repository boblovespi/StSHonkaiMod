package boblovespihonkaimod.cards.attacks;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.actions.NovaFissureAction;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.BurningPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

public class NovaFissure extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("novaFissure");
	public static final String IMG = DefaultMod.makeCardPath("Attack.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 3;
	private static final int DAMAGE = 26;
	private static final int DAMAGE_UPGRADE = 8;
	private static final int MAGIC = 6;
	private static final int UPGRADE = 4;

	// /STAT DECLARATION/


	public NovaFissure()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseDamage = DAMAGE;
		magicNumber = baseMagicNumber = MAGIC;
		// CardModifierManager.addModifier(this, new FreeIfCertainCostMod(5, COST));
		tags.add(CardTags.HEALING);
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeDamage(DAMAGE_UPGRADE);
			upgradeMagicNumber(UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		addToBot(new VFXAction(new ExplosionSmallEffect(m.hb_x, m.hb_y)));
		addToBot(new NovaFissureAction(m, new DamageInfo(p, damage, damageTypeForTurn), magicNumber));
	}
}
