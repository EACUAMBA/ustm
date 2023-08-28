using ContactsManager.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace ContactsManager.Controllers
{
    public class ContactController : Controller
    {
        private readonly ILogger<ContactController> _logger;
        private readonly ContactsManagerDbContext _contactsManagerDbContext;

        public ContactController(
            ILogger<ContactController> logger,
            ContactsManagerDbContext contactsManagerDbContext
            )
        {
            _logger = logger;
            this._contactsManagerDbContext = contactsManagerDbContext;
        }

        public IActionResult Index()
        {
            return View(
                model: this._contactsManagerDbContext.Contact.ToList()
            );
        }

        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Save()
        {
        
            var form = HttpContext.Request.Form;

            Contact contact = new Contact{
                Name = form["name"],
                PhoneNumber = form["phoneNumber"],
                Email = form["email"],
            };

            this._contactsManagerDbContext.Contact.Add(contact);
            this._contactsManagerDbContext.SaveChanges();

            return RedirectToAction(actionName: "Index", controllerName: "");
        }

            public IActionResult Update(int? id)
        {
            if(id == null){
                return NotFound();
            }

var contactToUpdate = this._contactsManagerDbContext.Contact.Where(c => c.Id == id).First();
            if(contactToUpdate == null){
                return NotFound();
            }
            var form = HttpContext.Request.Form;

            Contact contact = new Contact{
                Id = contactToUpdate.Id,
                Name = form["name"],
                PhoneNumber = form["phoneNumber"],
                Email = form["email"],
            };
            this._contactsManagerDbContext.ChangeTracker.Clear();
            this._contactsManagerDbContext.Contact.Update(contact);
            this._contactsManagerDbContext.SaveChanges();

            return RedirectToAction(actionName: "Index");
        }

        public IActionResult Edit(int? id)
        {
            Contact contact = this._contactsManagerDbContext.Contact.Where(c => c.Id == id).First();

            if(contact == null){
                return NotFound();
            }

            return View(
                model: contact
            );
        }

        public IActionResult DeleteQuestion(int? id)
        {
            var contactToDelete = this._contactsManagerDbContext.Contact.Where(c => c.Id == id).First();
            if(contactToDelete == null){
                return NotFound();
            }
            return View(
                model: contactToDelete
            );
        }

        public IActionResult Delete(int? id)
        {
            Contact c = this._contactsManagerDbContext.Contact.Where(c => c.Id == id).First();
            this._contactsManagerDbContext.ChangeTracker.Clear();
            this._contactsManagerDbContext.Contact.Remove(c);
            this._contactsManagerDbContext.SaveChanges();

            return RedirectToAction(actionName: "Index");
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}