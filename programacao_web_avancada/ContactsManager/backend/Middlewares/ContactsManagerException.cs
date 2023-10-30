namespace Middlewares
{
    public class ContactsManagerException : Exception
    {
        private readonly int _statusCodes;
        private readonly string _cause;

        public ContactsManagerException(int statusCodes, string cause)
        {
            this._statusCodes = statusCodes;
            this._cause = cause;
        }


        public int StatusCodes { get { return this._statusCodes; } }
        public string Cause { get { return this._cause; } }
    }
}