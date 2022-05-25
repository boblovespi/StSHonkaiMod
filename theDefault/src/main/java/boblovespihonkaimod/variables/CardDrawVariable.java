package boblovespihonkaimod.variables;

import basemod.abstracts.DynamicVariable;
import boblovespihonkaimod.DefaultMod;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class CardDrawVariable extends DynamicVariable
{
	@Override
	public String key()
	{
		return DefaultMod.makeID("Draw");
		// This is what you put between "!!" in your card strings to actually display the number.
		// You can name this anything (no spaces), but please pre-phase it with your mod name as otherwise mod conflicts can occur.
		// Remember, we're using makeID so it automatically puts "theDefault:" (or, your id) before the name.
	}

	@Override
	public boolean isModified(AbstractCard card)
	{
		return card.draw != card.baseDraw;

	}

	@Override
	public int value(AbstractCard card)
	{
		return card.draw;
	}

	@Override
	public int baseValue(AbstractCard card)
	{
		return card.baseDraw;
	}

	@Override
	public boolean upgraded(AbstractCard card)
	{
		return card.upgraded;
	}
}