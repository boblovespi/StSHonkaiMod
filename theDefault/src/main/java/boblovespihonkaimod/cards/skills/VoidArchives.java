package boblovespihonkaimod.cards.skills;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.actions.VoidArchivesAction;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;

public class VoidArchives extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("voidArchives");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 0;
	private static final int MAGIC = 1;
	private static final int MAGIC_UPGRADE = 1;
	private static final int REMOVE = 6;

	public VoidArchives()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		magicNumber = baseMagicNumber = MAGIC;
		exhaust = true;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeMagicNumber(MAGIC_UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		addToBot(new VoidArchivesAction(magicNumber));
	}
}
