export function formatDate(createAt: Date): string {
  const options: Intl.DateTimeFormatOptions = {
    month: 'short',
    day: 'numeric',
    year: 'numeric',
  };

  return createAt.toLocaleDateString('en-US', options);
}

export function filterSlotsByPaymentType(slots:any[], paymentType:any) {
  // Use Array.filter to filter slots based on paymentType
  return slots.filter(slot => {
    // Check if the slot has taxes
    if (slot.taxes && slot.taxes.length > 0) {
      // Check if any tax has the specified paymentType
      // @ts-ignore
      return slot.taxes.some(tax => tax.payementType === paymentType);
    }
    return false;
  });
}

export function convertDateFormat(inputDate: string): string {
  const inputDateTime = new Date(inputDate);
  const options: Intl.DateTimeFormatOptions = { day: 'numeric', month: 'short', year: 'numeric' };

  return inputDateTime.toLocaleDateString('en-US', options);
}

export function formatCreatedAt(components: number[]): string {
  const [year, month, day, hour, minute, second, milliseconds] = components;
  const date = new Date(
    year,
    month - 1,
    day - 4,
    hour,
    minute,
    second,
    milliseconds
  );
  return date.toLocaleDateString('en-US', {
    month: 'short',
    day: 'numeric',
    year: 'numeric',
  });
}
